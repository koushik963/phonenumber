import { Component, OnInit } from '@angular/core';
import { AppService } from './app-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'phonenumber';

  count: any;

  phoneNumber: any;

  phoneNumbers: any;

  currentPage: number = 1;
  totalPages: number;
  pageSize: number = 10;

  data: any;

  invalidNumber: boolean = true;

  constructor(private appService: AppService) { }

  ngOnInit() {

  }

  generateNumber() {
    console.log(this.phoneNumber);
    if (!!this.phoneNumber) {
      this.appService.generatePhoneNumbers(this.phoneNumber).subscribe((resp) => {
        this.count = resp;
        this.setTotalPages();
        console.log(resp);
        if (this.count) {
          this.getPhoneNumbers(this.phoneNumber, 0);
        }
        // this.phoneNumbers = resp;
        // this.count
      });
    }
  }

  getPhoneNumbers(phoneNumber, pageNumber) {
    this.appService.getPhoneNumbers(phoneNumber, pageNumber).subscribe(resp => {
      this.phoneNumbers = resp;
      console.log(resp);
    });
  }

  setTotalPages() {
    this.totalPages = Math.floor((this.count / this.pageSize)) + (this.count % this.pageSize == 0 ? 0 : 1);
  }

  selectPage(selectedPage) {
    this.currentPage = selectedPage;
    this.getPhoneNumbers(this.phoneNumber, selectedPage);
  }


  onClickButton(row) {
    alert('Selected ID = ' + row.id + ' and status = ' + row.status);
  }


  createPageNumber() {
    let leftSlot = 4, rightSlot = 3, leftPage, rightPage, openSlot = 0;
    if (this.currentPage - leftSlot > 1) {
      leftPage = this.currentPage - leftSlot;
    } else {
      openSlot = leftSlot - (this.currentPage - 2);
      leftPage = 2;
    }
    rightSlot += openSlot;
    if (rightSlot + this.currentPage < this.totalPages) {
      rightPage = this.currentPage + rightSlot;
    } else {
      openSlot = rightSlot + this.currentPage - this.totalPages + 1;
      rightPage = this.totalPages - 1;
    }
    if (leftPage > 2) {
      if (leftPage - openSlot > 2) {
        leftPage = leftPage - openSlot;
      } else {
        leftPage = 1;
      }
    }

    return Array.from(Array(rightPage - leftPage + 1), (_, i) => leftPage + i);
  }

  pageJump(page: number): void {
    this.selectPage(page);
  }

  pageDecrement(num: number): void {
    num = num || 1;
    if (this.currentPage - num >= 1) {
      this.currentPage = this.currentPage - num;
    } else {
      this.currentPage = 1;
    }
    this.pageJump(this.currentPage);
  }

  pageIncrement(num: number): void {
    num = num || 1;
    if (this.currentPage + num <= this.totalPages) {
      this.currentPage = this.currentPage + num;
    } else {
      this.currentPage = this.totalPages;
    }
    this.pageJump(this.currentPage);
  }

  validateInputNumber() {
    if(this.phoneNumber.length == 0){
      this.invalidNumber = true;
      return true;
    }
    if (this.phoneNumber.match(/^([0-9]+)$/)) {
      if (this.phoneNumber.length == 7 || this.phoneNumber.length == 10) {
        this.invalidNumber = false;
        return true;
      }
    }
    this.invalidNumber = true;
    return false;
  }
}
