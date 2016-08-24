class Employee {

    double calculateBonus(int yearsOfExperience)
    {
        double bonus = 1.00;
        if (yearsOfExperience > 0) {
            if (yearsOfExperience <= 2) bonus = 1.05;
            else if (yearsOfExperience > 2 && yearsOfExperience < 5) bonus = 1.12;
            else if (yearsOfExperience >= 5 && yearsOfExperience <= 7) bonus = 1.15;
            else if (yearsOfExperience > 7 && yearsOfExperience <= 11) bonus = 1.30;
            else bonus = 1.50;
            return bonus;
        }
        else return bonus;
    }
}


