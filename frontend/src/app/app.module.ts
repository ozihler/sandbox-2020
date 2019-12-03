import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {routes} from "./routes";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  ButtonModule,
  CodeHighlighterModule,
  ConfirmDialogModule,
  DragDropModule,
  PanelModule,
  TabViewModule
} from "primeng/primeng";
import {TableModule} from "primeng/table";
import {CommonModule} from "@angular/common";
import {WikiComponent} from './wiki/adapter/presentation/overview/wiki.component';
import {WikiPageComponent} from './wiki/adapter/presentation/wiki-page/wiki-page.component';
import {WikiPageTitleComponent} from './wiki/adapter/presentation/wiki-page/wiki-page-title.component';
import {WikiPageBodyComponent} from './wiki/adapter/presentation/wiki-page/wiki-page-body.component';
import {RouteButtonComponent} from './wiki/adapter/presentation/route-button.component';
import {OverviewTableComponent} from './wiki/adapter/presentation/overview/overview-table/overview-table.component';

@NgModule({
  declarations: [
    AppComponent,
    WikiComponent,
    WikiPageComponent,
    WikiPageTitleComponent,
    WikiPageBodyComponent,
    RouteButtonComponent,
    OverviewTableComponent

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    DragDropModule,
    TableModule,
    PanelModule,
    TabViewModule,
    CommonModule,
    ButtonModule,
    ConfirmDialogModule,
    CodeHighlighterModule
  ],
  providers: [HttpClient,
    {provide: "windowObject", useValue: window}],
  bootstrap: [AppComponent]
})
export class AppModule { //
}
