import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink  } from '@angular/router';

@Component({
  imports: [RouterOutlet, RouterLink],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  title=signal('Diceva Juegos de mesa')
  footer=signal('Univiersidad del Quindio')
}
