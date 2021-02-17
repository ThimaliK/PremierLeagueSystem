import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateMatchComponent } from './generate-match.component';

describe('GenerateMatchComponent', () => {
  let component: GenerateMatchComponent;
  let fixture: ComponentFixture<GenerateMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerateMatchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerateMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
