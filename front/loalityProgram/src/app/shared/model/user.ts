import {Bonus} from "./bonus";
import {Rent} from "./rent";
import {s} from "@angular/core/src/render3";

export class User {
  id: number;
  imagePath : string;
  surname: string;
  name: string;
  secondName: string;
  phone: string;
  email:string;
  dateOfRegistration: string;
  dateOfBirth: string;
  socialMedia: string;
  memberDate: string;
  cardId: number;
  isMember: boolean;
  lastVisit: string;
  dateOfMember: string;
  bonus: Bonus[];
  isActive: boolean;
  rents: Rent[];
}
