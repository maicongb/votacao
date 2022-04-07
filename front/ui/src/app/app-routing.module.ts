import { EnqueteComponent } from './pages/enquete/enquete.component';
import { CandidatoCadastroComponent } from './pages/candidato/candidatoCadastro/candidatoCadastro.component';
import { CandidatoComponent } from './pages/candidato/candidato.component';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './pages/profile/profile.component';
import { AboutComponent } from './pages/about/about.component';
import { HelpComponent } from './pages/help/help.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'home',
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'candidato',
    component: CandidatoComponent,
    children: [
      {
        path: 'cadastro/:id',
        component: CandidatoCadastroComponent,
      },
    ]
  },
  {
    path: 'enquete',
    component: EnqueteComponent,
  },
  {
    path: 'profile',
    component: ProfileComponent,
  },
  {
    path: 'about',
    component: AboutComponent,
  },
  {
    path: 'help',
    component: HelpComponent,
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
