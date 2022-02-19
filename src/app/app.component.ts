import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  items: MenuItem[] = [];

  constructor(private router: Router) {
  }

  route() {
    this.router.navigate(['/customer-list'])
  }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Users',
        icon: 'pi pi-fw pi-user',
        routerLink: '/user-list'
      },
      {
        label: 'Customers',
        icon: 'pi pi-fw pi-user',
        routerLink: ['/customer-list']
      },
      {
        label: 'Login',
        icon: 'pi pi-fw pi-user',
        routerLink: ['/login']
      }
    ]
  }


}
