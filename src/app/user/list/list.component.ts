import {Component, OnInit} from '@angular/core';
import {UserService} from "../shared/user.service";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  cols: any[] = [
    {field: 'id', header: 'ID', sortable: true},
    {field: 'firstName', header: 'First Name', sortable: true},
    {field: 'lastName', header: 'Last Name', sortable: false},
    {field: 'email', header: 'Email', sortable: true},
    {field: 'phoneNumber', header: 'Phone Number', sortable: false},
    {field: 'username', header: 'Username', sortable: true},
  ];

  isLoading = false;
  total: number = 0;
  list: any[] = [];

  constructor(private userService: UserService) {
  }

  initUsers() {
    this.isLoading = true;
    this.userService.getAllUsers().subscribe(res => {
      if (res) {
        this.list = res;
        this.total = this.list.length;
      }
      this.isLoading = false;
    });
  }


  ngOnInit(): void {
    this.initUsers();
  }

  update(item: any) {

    this.isLoading = true;
    if (item.enabled) {
      item.enabled = false;
    } else {
      item.enabled = true;
    }
    this.userService.updateUser(item).subscribe((res: any) => {
      this.isLoading = false;
      this.initUsers();
    });
  }

}
