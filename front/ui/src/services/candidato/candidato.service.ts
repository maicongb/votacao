import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Candidato } from 'src/models/candidato';

@Injectable({
  providedIn: 'root'
})
export class CandidatoService {

  baseURL = 'https://localhost:7009/api/Candidato';
  constructor(private http: HttpClient) { }

  public getAll(): Observable<Candidato[]>{
    return this.http.get<Candidato[]>(this.baseURL);
  }

  public getById(id: number ): Observable<Candidato>{
    return this.http.get<Candidato>(`${this.baseURL}/${id}`);
  }

  public post(candidato: Candidato ): Observable<Candidato>{
    return this.http.post<Candidato>(this.baseURL, candidato);
  }

  public put( candidato: Candidato): Observable<Candidato[]>{
    return this.http.put<Candidato[]>(`${this.baseURL}`, candidato);
  }

  public delete(id: number): Observable<any>{
    return this.http.delete<string>(`${this.baseURL}/${id}`);
  }


}
