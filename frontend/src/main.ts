import {bootstrapApplication} from '@angular/platform-browser';
import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClient,HttpClientModule} from '@angular/common/http';

@Component({
 selector:'app-root',
 standalone:true,
 imports:[CommonModule,FormsModule,HttpClientModule],
 template:`
 <main>
 <h1>Event-Driven Order Platform</h1>
 <section>
 <h2>Create Order</h2>
 <input [(ngModel)]="customerId" placeholder="Customer ID">
 <input [(ngModel)]="sku" placeholder="SKU">
 <input type="number" [(ngModel)]="quantity" placeholder="Quantity">
 <input type="number" [(ngModel)]="amount" placeholder="Amount">
 <input [(ngModel)]="currency" placeholder="Currency">
 <button (click)="create()">Create Order</button>
 </section>
 <section *ngIf="result"><h2>Order</h2><pre>{{result | json}}</pre></section>
 </main>`
})
class AppComponent{
 customerId='customer-1'; sku='SKU-LAPTOP-001'; quantity=1; amount=999; currency='USD'; result:any;
 constructor(private http:HttpClient){}
 create(){this.http.post('/api/v1/orders',{customerId:this.customerId,sku:this.sku,quantity:this.quantity,totalAmount:this.amount,currency:this.currency}).subscribe(v=>this.result=v);}
}
bootstrapApplication(AppComponent);