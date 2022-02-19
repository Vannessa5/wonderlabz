import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../shared/customer-service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-customer',
  templateUrl: './list-customer.component.html',
  styleUrls: ['./list-customer.component.css']
})
export class ListCustomerComponent implements OnInit {

  cols: any[] = [
    {field: 'id', header: 'ID', sortable: true},
    {field: 'firstName', header: 'First Name', sortable: true},
    {field: 'lastName', header: 'Last Name', sortable: false},
    {field: 'email', header: 'Email', sortable: true},
    {field: 'phoneNumber', header: 'Phone Number', sortable: false},
  ]

  isLoading = false;
  list: any[] = [];
  total = 0;
  displayModal = false;
  headerOption = ""

  form: FormGroup = new FormGroup({})
  formIsInvalid = false;

  constructor(private customerService: CustomerService, private formBuilder: FormBuilder, private router: Router) {

    this.form = this.formBuilder.group({
      reference: [null, [Validators.required]],
      amount: [null, [Validators.required]],
      accountNumber: [null, [Validators.required]],
      accountType: [null, [Validators.required]],
    })
  }

  ngOnInit(): void {
    this.getCustomers();
  }


  getCustomers() {
    this.customerService.getAllCustomers().subscribe((response: any[]) => {
      this.list = response;
      console.log('customers', response)
    })
  }

  checkForm(form: FormGroup) {

    if (!this.form.valid) {
      this.form.controls['reference'].markAllAsTouched()
      this.form.controls['accountNumber'].markAllAsTouched()
      this.form.controls['amount'].markAllAsTouched()
      this.form.controls['accountType'].markAllAsTouched()
      this.formIsInvalid = true;
    } else {
      this.formIsInvalid = false;

      this.submit();
    }
  }

  submit() {
    let customer = this.form.value;
    if (this.headerOption.toLowerCase().includes('deposit')) {
      this.customerService.deposit(customer).subscribe((response: any) => {
        this.displayModal = false
        this.showSuccess('Deposit successfully made')
      }, err => {
        this.showFailed(err.message);
      })
    } else {
      this.customerService.withdraw(customer).subscribe((response: any) => {
        this.displayModal = false
        if (response.message) {
          this.showFailed(response.message)
        } else {
          this.showSuccess('Withdrawal successfully made')
        }
      }, err => {
        this.showFailed(err.message);
      })
    }
  }

  showModalDialog(options: any, accountNumber: number) {
    this.form.controls['accountNumber'].patchValue(accountNumber)
    this.headerOption = options;
    this.displayModal = true;
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
