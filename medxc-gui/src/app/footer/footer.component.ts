import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  template: `<p class='myfooter'>Medical examination calendar #Date: {{currentDate | date:'yyyy-MM-dd'}}</p>`,
  styles: [
    `.myfooter { background-color:#91bcc1;  }
      p {position:fixed;bottom:0;width:100%;text-align:center}
    `
  ]
})
export class FooterComponent implements OnInit {
  currentDate: Date;
  constructor() { }

  ngOnInit() {
    this.currentDate = new Date();
  }

}
