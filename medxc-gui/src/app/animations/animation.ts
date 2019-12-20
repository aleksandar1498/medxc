import { trigger, state, style, animate, transition, query, group, animateChild } from '@angular/animations';

const ease = '300ms ease-out';
export const slideInAnimation =
  trigger('routeAnimations', [
    transition('PatientPage <=> AppointmentsPage', [
      style({ position: 'relative' }),
      query(':enter, :leave', [
        style({
          position: 'absolute',
          top: 0,
          left: 0,
          width: '100%'
        })
      ]),
      query(':enter', [
        style({ left: '-100%' })
      ]),
      query(':leave', animateChild()),
      group([
        query(':leave', [
          animate(ease, style({ left: '100%' }))
        ]),
        query(':enter', [
          animate(ease, style({ left: '0%' }))
        ])
      ]),
      query(':enter', animateChild()),
    ]),
    transition('AppointmentsPage <=> PatientPage', [
      style({ position: 'relative' }),
      query(':enter, :leave', [
        style({
          position: 'absolute',
          top: 0,
          right: 0,
          width: '100%'
        })
      ]),
      query(':enter', [
        style({ right: '100%' })
      ]),
      query(':leave', animateChild()),
      group([
        query(':leave', [
          animate(ease, style({ right: '100%' }))
        ]),
        query(':enter', [
          animate(ease, style({ right: '0%' }))
        ])
      ]),
      query(':enter', animateChild()),
    ]),
  ]);
