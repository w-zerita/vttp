import { Component, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Registration } from 'src/app/models';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Output()
  onNewRegistration = new Subject<Registration>()

  @Input()
  registration!: Registration
  
  regForm!: FormGroup

  // use form builder service to build form model
  // private is the shortcut that defines fb to be a memmber of the class, to be used in ngOnInit
  constructor(private fb: FormBuilder) {
    console.log(">>> fb: ", fb)
    this.fb = fb
  }

  // create object using {}
  ngOnInit(): void {
    this.regForm = this.fb.group({ // put validation in the second parameter inside []
      name: this.fb.control(this.registration?.name, [Validators.required, Validators.minLength(3)]),
      email: this.fb.control(this.registration?.email, [Validators.required, Validators.email]),
      gender: this.fb.control(this.registration?.gender),
      newsletter: this.fb.control(this.registration?.newsletter) // .? means if not defined, ignore
    })
  }

  processForm() {
    console.log(">>> submit button is clicked")
    console.log(">>> regForm: ", this.regForm.value)
    const reg: Registration = this.regForm.value as Registration // controls must have the same naming as interface
    /*
    const reg: Registration = {
      name: this.regForm.value.name,
      email: this.regForm.value.email,
      gender: this.regForm.value.gender,
      newsletter: this.regForm.value.newsletter
    }
    */
   this.onNewRegistration.next(reg)
  }
}
