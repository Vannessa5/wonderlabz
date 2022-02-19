import { Component, OnInit } from '@angular/core';
import swal from "sweetalert2";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MenuItem} from "primeng/api";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../shared/user.service";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {


  isLoading = false;
  formIsInvalid = false;
  form: FormGroup = new FormGroup({});
  username: any;
  process: any;


  constructor(private formBuilder: FormBuilder, activatedRoute: ActivatedRoute, fb: FormBuilder, private router: Router,
              private userService: UserService) {
    this.form = this.formBuilder.group({
      id: [null],
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')]],
      dateOfBirth: [null],
      userLocked: [null],
      authorities: [[]],
      enabled: [false],
      createdBy: [window.localStorage.getItem('encoded_username')],
      createdDate: [new Date()],
      lastModifiedBy: [null],
      lastModifiedDate: [null],
      lastLoginDate: [null],
      phoneNumber: [null],
    });

    this.username = activatedRoute.snapshot.params['id'];


  }

  ngOnInit(): void {
    this.initUser(this.username);
  }

  initUser(username: number) {
    if (username) {
      this.userService.getUserByUsername(this.username).subscribe(res => {
        this.form.patchValue(res);
      });
    }
  }

  routeToList(): void {
    this.router.navigate(['/user-list']);
  }

  checkForm(form: FormGroup) {
    if (!this.form.valid) {
      this.form.controls['id'].markAsTouched();
      this.form.controls['firstName'].markAsTouched();
      this.form.controls['lastName'].markAsTouched();
      this.form.controls['email'].markAsTouched();
      this.form.controls['phoneNumber'].markAsTouched();
      this.form.controls['username'].markAsTouched();
      this.form.controls['password'].markAsTouched();
      this.form.controls['idNumber'].markAsTouched();
      this.formIsInvalid = true;
      console.log('not valid ')
    } else {
      this.isLoading = true;
      this.formIsInvalid = false;
      console.log('vald valid valid ')
      this.submit();
    }
  }

  submit() {
    this.isLoading = true;
    if (this.username) {
      this.form.controls['username'].patchValue(this.username);
      this.userService.updateUser(this.form.value).subscribe(res => {
        this.isLoading = false;
        this.showSuccess("Updated!.");
        this.form.patchValue(res);
      }, error => {
        this.showFailed(error);
      });
    } else {
      this.userService.saveUser(this.form.value).subscribe(res => {
        this.isLoading = false;
        this.showSuccess("Created!.")
        this.form.patchValue(res);
      }, error => {
        this.showFailed(error);
      });
    }

  }

  showSuccess(message: string) {
    swal.fire({
      title: 'Success!',
      text: `User Successfully ${message}`,
      icon: 'success',
      target: 'body',
      confirmButtonText: 'OK',
    }).then(() => {
      this.router.navigate(['/user-list']);
    });
  }

  showFailed(message: string) {
    swal.fire({
      title: 'Error',
      text: `${message}`,
      icon: 'error',
      target: 'body',
      confirmButtonText: 'OK',
    })
  }
}
