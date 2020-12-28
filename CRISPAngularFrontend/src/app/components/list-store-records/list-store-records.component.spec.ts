import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListStoreRecordsComponent } from './list-store-records.component';

describe('ListStoreRecordsComponent', () => {
  let component: ListStoreRecordsComponent;
  let fixture: ComponentFixture<ListStoreRecordsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListStoreRecordsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListStoreRecordsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
