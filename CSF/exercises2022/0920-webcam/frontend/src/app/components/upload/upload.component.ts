import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { WebcamService } from 'src/app/services/webcam.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  imgSrc!: string
  form!: FormGroup

  constructor(private webcamSvc: WebcamService, private router: Router,
    private fb: FormBuilder) { }

  ngOnInit(): void { // image is lost when page is refreshed
    if (!this.webcamSvc.img) {
      this.router.navigate(['/']) // go back to webcam component if there is no image
      return
    }
    this.imgSrc = this.webcamSvc.img
    this.form = this.createForm()
  }

  createForm() {
    return this.fb.group({
      // img: this.fb.control('', [Validators.required]),
      title: this.fb.control('', [Validators.required, Validators.minLength(3)])
    })
  }

  post() {
    const title = this.form.get('title')?.value
    console.log('>>> title: ', title)
    this.webcamSvc.upload(title)
      .then(result =>{
        console.log('>>. result: ', result)
        this.router.navigate(['/'])
      }
      ).catch((error: HttpErrorResponse) =>{
        console.error('>>> error: ', error
        )}
      )
  }

}
