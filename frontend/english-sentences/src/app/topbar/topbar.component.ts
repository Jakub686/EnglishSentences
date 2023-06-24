import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {
  title = 'english-sentences';
  login: string | null = '';

  constructor() {}

  ngOnInit() {
    this.getLoginFromLocalStorage(); // Get initial login value

    // Subscribe to localStorage changes
    window.addEventListener('storage', this.onStorageChange.bind(this));
  }

  getLoginFromLocalStorage() {
    this.login = localStorage.getItem('login') as string;
    console.log('login top bar ' + this.login);
  }

  onStorageChange(event: StorageEvent) {
    if (event.key === 'login') {
      this.getLoginFromLocalStorage(); // Update login value
      console.log('login top bar updated: ' + this.login);
    }
  }
}
