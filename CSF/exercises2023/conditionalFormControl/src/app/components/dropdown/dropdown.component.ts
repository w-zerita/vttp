import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.css']
})
export class DropdownComponent implements OnInit {
  form!: FormGroup
  formControl!: FormControl
  dd1Options: string[] = ['option1', 'option2', 'option3', 'option4']
  dd2Options: string[] = ['option1', 'option2']
  dd3Options: string[] = ['option1', 'option2']
  dd4Options: string[] = ['option1', 'option2']
  dd5Options: string[] = ['option1', 'option2', 'option3']
  dd6Options: string[] = ['option1', 'option2']

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  changeOptions(e: any) {
    console.log('change option: ', e.target.value);
    this.form.get('dropdown1')?.setValue(e.target.value, { onlySelf: true })
    switch(e.target.value) {
      case 'option3':
      case 'option4':
        console.log('update options for case 1 and 4');
        this.dd2Options = ['optionA']
        this.dd3Options = ['optionB']
        this.dd4Options = ['optionA', 'optionB']
        this.dd5Options = ['optionA', 'optionB', 'optionC']
        this.form = this.createForm()
        this.form.patchValue({dropdown1: e.target.value})
        break;
      case 'option2':
        this.dd2Options = ['option1', 'option2']
        this.dd3Options = ['option1', 'option2']
        this.dd4Options = ['option2']
        this.dd5Options = ['option1']
        this.form.addControl('dropdown6', new FormControl(this.dd6Options[0], [Validators.required]))
        break;
      default:
        this.dd2Options = ['option1', 'option2']
        this.dd3Options = ['option1', 'option2']
        this.dd4Options = ['option1', 'option2']
        this.dd5Options = ['option1', 'option2', 'option3']
        this.form = this.createForm()
        this.form.patchValue({dropdown1: e.target.value})
        break;
    }
    console.log('form: ', this.form.value);
  }

  processForm() {
    const data = this.form.value
    console.log(">>> data: ", data);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      dropdown1: this.fb.control<string>(this.dd1Options[0], [Validators.required]),
      dropdown2: this.fb.control<string>(this.dd2Options[0], [Validators.required]),
      dropdown3: this.fb.control<string>(this.dd3Options[0], [Validators.required]),
      dropdown4: this.fb.control<string>(this.dd4Options[0], [Validators.required]),
      dropdown5: this.fb.control<string>(this.dd5Options[0], [Validators.required])
    })
  }
}
