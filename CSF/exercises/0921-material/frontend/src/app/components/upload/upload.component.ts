import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UploadService } from 'src/app/services/upload.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  @ViewChild('file')
  file!: ElementRef

  form!: FormGroup

  constructor(private fb: FormBuilder, private uploadSvc: UploadService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      title: this.fb.control<string>('', [Validators.required]),
      file: this.fb.control<any>('', [Validators.required])
    })
  }

  doUpload() {
    console.log('>>> upload: ', this.form.value)
    console.log('>>> doUpload: ', this.file.nativeElement.files[0])

    const myFile = this.file.nativeElement.files[0]
    const title = this.form.get('title')?.value

    this.uploadSvc.upload(title, myFile)
      .then(result => {
        console.log('>>> result: ', result)
      }).catch(error => {
        console.error('>>> error: ', error)
      })
  }
}
