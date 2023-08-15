import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluateAttendanceComponent } from './evaluate-attendance.component';

describe('EvaluateAttendanceComponent', () => {
  let component: EvaluateAttendanceComponent;
  let fixture: ComponentFixture<EvaluateAttendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvaluateAttendanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EvaluateAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
