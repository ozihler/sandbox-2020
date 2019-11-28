import {Routes} from "@angular/router";
import {WikiComponent} from "./wiki/wiki.component";


export const routes: Routes = [
  {path: 'wiki', component: WikiComponent},
  {path: '', redirectTo: 'wiki', pathMatch: 'full'},
  {path: '**', redirectTo: 'wiki', pathMatch: 'full'}
];
