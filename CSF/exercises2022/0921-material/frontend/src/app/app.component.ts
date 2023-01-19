import { Component, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  @ViewChild(MatDrawer)
  drawer!: MatDrawer

  toggleSidebar() {
    console.log('>> toggle sidebar', this.drawer)
    this.drawer.toggle()
  }
}
