import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CustomerService} from "../shared/customer-service";
import swal from "sweetalert2";

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {

  form: FormGroup = new FormGroup({});

  accountForm: FormGroup = new FormGroup({});
  accountTypeForm: FormGroup = new FormGroup({});
  formIsInvalid = false;
  isLoading = false;
  process = ""
  accountTypeList: AccountType[] = [];

  constructor(private formBuilder: FormBuilder, private router: Router, private customerService: CustomerService) {
    this.buildForm();
  }


  buildForm() {
    this.accountTypeForm = this.formBuilder.group({
      id: [null],
      name: [null, [Validators.required]],
      balance: [null, [Validators.required]],
    })

    this.accountForm = this.formBuilder.group({
      id: [null],
      accountNumber: [null, [Validators.required]],
      accountTypes: [[this.accountTypeForm]]
    })

    this.form = this.formBuilder.group({
      id: [null],
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      phoneNumber: [null, [Validators.required]],
      email: [null, [Validators.required]],
      account: [this.accountForm],
    });
  }

  ngOnInit(): void {
  }

  checkForm(form: FormGroup) {
    if (!this.form.valid || !this.accountForm.valid || !this.accountTypeForm.valid) {
      if (!this.form.valid) {
        this.form.controls['firstName'].markAllAsTouched();
        this.form.controls['lastName'].markAllAsTouched();
        this.form.controls['phoneNumber'].markAllAsTouched();
        this.form.controls['email'].markAllAsTouched();
      }
      if (!this.accountForm.valid) {
        this.accountForm.controls['accountNumber'].markAllAsTouched();
      }

      if (!this.accountTypeForm.valid) {
        this.accountTypeForm.controls['name'].markAllAsTouched();
        this.accountTypeForm.controls['balance'].markAllAsTouched();
      }
      this.formIsInvalid = true;

    } else {
      this.formIsInvalid = false;
      this.submitForm();
    }


  }

  submitForm() {
    console.log('Ohhh yes')


    let customer = this.form.value;

    customer.account = this.accountForm.value;

    if (this.accountTypeForm.valid) {
      this.accountTypeList.push(this.accountTypeForm.value);
    }

    customer.account.accountTypes = this.accountTypeList;
    console.log('customer', customer)

    if (customer.id) {
      console.log('we need to update customer', customer)
    } else {

      this.customerService.createCustomer(customer).subscribe((response: any) => {
        if (response.message) {
          this.accountTypeList = [];
          this.showFailed(response.message)
        } else {
          this.showSuccess('Successfully Opened an Account')
        }
      }, err => {
        this.showFailed(err.message)
      })
    }
  }

  addAccountType() {
    this.accountTypeList = [];
    let accountType = this.accountTypeForm.value;
    this.accountTypeList.push(accountType);

    this.accountTypeForm.controls['name'].patchValue(null);
    this.accountTypeForm.controls['balance'].patchValue(null);
    this.accountTypeForm.clearValidators()
  }

  accountTypeInvalid() {

    return false;
  }

  routeToList() {
    this.router.navigate(['/user-list']);
  }


  showSuccess(message: string) {
    swal.fire({
      title: 'Success!',
      text: `${message}`,
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


export class AccountType {

  constructor(public id: number,
              public name: string,
              public balance: number) {
  }


}
