import {Routes} from "@angular/router";
import {LibraryComponent} from "./library/library.component";
import {WikiComponent} from "./wiki/wiki.component";


export const routes: Routes = [
  {path: 'wiki', component: WikiComponent},
  {path: 'library', component: LibraryComponent},
  {path: '', redirectTo: 'library', pathMatch: 'full'},
  {path: '**', redirectTo: 'library', pathMatch: 'full'}
];
