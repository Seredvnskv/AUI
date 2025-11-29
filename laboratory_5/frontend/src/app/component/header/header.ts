import { Component, signal, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.html',
  styleUrl: './header.css',
})

export class Header {
  date = signal(new Date());
  private timerId?: number;

  /*
  ngOnInit() {
    this.timerId = window.setInterval(() => {
      this.date.set(new Date());
    }, 1000);
  }

  ngOnDestroy() {
    if (this.timerId) window.clearInterval(this.timerId);
  }
  */
}
