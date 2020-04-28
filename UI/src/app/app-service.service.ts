import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const API_URL = 'http://localhost:4200/phonenumber/';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  generatePhoneNumbers(phoneNumber: number) {
    return this.http.post(API_URL + 'generate/' + phoneNumber, {});
  }

  getPhoneNumbers(phoneNumber: number, pageNumber: number) {
    return this.http.get(API_URL + phoneNumber + '/' + pageNumber);
  }
}
