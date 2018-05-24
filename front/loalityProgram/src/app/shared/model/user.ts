import {Bonus} from "./bonus";
import {Rent} from "./rent";

export class User {
  id: number;
  image : string;
  surname: string;
  name: string;
  secondName: string;
  phone: string;
  dateOfBirth: string;
  socialMedia: string[];
  memberDate: string;
  cardId: number;
  isMember: boolean;
  lastVisit: string;
  numberOfVisits: number;
  bonus: Bonus[];
  isActive: boolean;
  rents: Rent[];
}
