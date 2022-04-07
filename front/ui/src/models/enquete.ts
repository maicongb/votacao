import { Candidato } from './candidato';
import { Votante } from './votante';
export interface Enquete {
  id: number;
  nome: string;
  descricao: string;
  dataInicial: string;
  dataFinal: number;
  candidatos: Candidato[];
  votantes: Votante[];
}
