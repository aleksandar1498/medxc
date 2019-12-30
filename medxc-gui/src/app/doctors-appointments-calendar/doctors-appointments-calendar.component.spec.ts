import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorsAppointmentsCalendarComponent } from './doctors-appointments-calendar.component';

describe('DoctorsAppointmentsCalendarComponent', () => {
  let component: DoctorsAppointmentsCalendarComponent;
  let fixture: ComponentFixture<DoctorsAppointmentsCalendarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorsAppointmentsCalendarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorsAppointmentsCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
