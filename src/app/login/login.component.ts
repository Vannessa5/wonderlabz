import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import swal from 'sweetalert2';
import {Router} from "@angular/router";
import {LoginService} from "./service/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public form: FormGroup;
  formIsInValid = false;
  isLoading = false;
  message = "";
  showLoginWithOpt = false;
  isEmailValid = false;


  constructor(private formBuilder: FormBuilder, private messageService: MessageService, private router: Router,
              private loginService: LoginService) {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });


  }


  ngOnInit(): void {
  }

  checkForm(form: FormGroup): void {
    if (!this.form.valid) {
      this.form.controls['username'].markAsTouched();
      this.form.controls['password'].markAsTouched();
      this.formIsInValid = true;
    } else {
      this.isLoading = true;
      this.formIsInValid = false;
      this.submit();
    }

  }

  submit() {
    this.loginService.authenticate(this.form.value).subscribe(response => {
      const token = response;
      window.localStorage.setItem('token', token.token);
      window.localStorage.setItem('encoded_username', this.form.get('username')?.value);
      window.localStorage.setItem('authenticated', 'yes');

      this.showSuccess1("Login")
    }, error => {
      this.isLoading = false
      this.message = JSON.parse(error).error.text;
      this.showWarning(this.message);
    })
  }


  showWarning(message: string) {
    this.messageService.add({
      severity: 'error',
      summary: 'Warning',
      detail: message,
    });
  }


  showSuccess1(message: string) {
    swal.fire({
      title: 'Success!',
      text: `User Successfully Login`,
      icon: 'success',
      target: 'body',
      confirmButtonText: 'OK',
    }).then(() => {
      this.router.navigate([`/user-list`]);
    });
  }

  showFailed(message: string) {
    swal.fire({
      title: 'Error',
      text: message,
      icon: 'error',
      target: 'body',
      confirmButtonText: 'OK',
    }).then(() => {
      this.router.navigate(['/login']);
    });
  }


}
