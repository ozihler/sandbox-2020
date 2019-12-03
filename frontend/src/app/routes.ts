import {Routes} from "@angular/router";
import {WikiComponent} from "./wiki/adapter/presentation/overview/wiki.component";
import {WikiPageComponent} from "./wiki/adapter/presentation/wiki-page/wiki-page.component";


export const routes: Routes = [
  {path: 'wiki-page', component: WikiPageComponent},
  {path: 'overview', component: WikiComponent},
  {path: '', redirectTo: 'overview', pathMatch: 'full'},
  {path: '**', redirectTo: 'overview', pathMatch: 'full'}
];
