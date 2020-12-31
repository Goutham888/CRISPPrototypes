import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordRequestComponent } from './record-request.component';

describe('RecordRequestComponent', () => {
  let component: RecordRequestComponent;
  let fixture: ComponentFixture<RecordRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecordRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
