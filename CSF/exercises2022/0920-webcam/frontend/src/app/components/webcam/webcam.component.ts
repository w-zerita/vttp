import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WebcamImage } from 'ngx-webcam';
import { Observable, Subject } from 'rxjs';
import { WebcamService } from 'src/app/services/webcam.service';

@Component({
  selector: 'app-webcam',
  templateUrl: './webcam.component.html',
  styleUrls: ['./webcam.component.css']
})
export class WebcamComponent implements OnInit {

  width!: number
  captureImage = '/assets/placeholder.png'

  snap = new Subject<void>()
  snapObs = this.snap.asObservable()

  constructor(private router: Router, private webcamSvc: WebcamService) { }

  ngOnInit(): void {
    this.width = Math.floor(window.innerWidth / 3)
  }

  capture() {
    console.log('>>> capture')
    this.snap.next()
  }

  snapshot(img: WebcamImage) {
    console.log(">>> image: ", img)
    this.captureImage = img.imageAsDataUrl
    this.webcamSvc.img = img.imageAsDataUrl
    this.router.navigate(['/upload'])
  }

}
