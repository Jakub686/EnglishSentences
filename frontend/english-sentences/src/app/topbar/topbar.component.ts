import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {
  title = 'english-sentences';
  email: string | null = '';

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.getLoginFromLocalStorage(); // Get initial login value

    // Subscribe to localStorage changes
    window.addEventListener('storage', this.onStorageChange.bind(this));
  }

  getLoginFromLocalStorage() {
    this.email = localStorage.getItem('email') as string;
    console.log('login top bar ' + this.email);
  }

  onStorageChange(event: StorageEvent) {
    if (event.key === 'email') {
      this.getLoginFromLocalStorage(); // Update login value
      console.log('login top bar updated: ' + this.email);
    }
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/main']);
  }
}
