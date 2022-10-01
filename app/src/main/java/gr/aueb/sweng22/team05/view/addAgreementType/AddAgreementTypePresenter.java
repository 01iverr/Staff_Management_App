package gr.aueb.sweng22.team05.view.addAgreementType;

import gr.aueb.sweng22.team05.dao.AgreementTypeDAO;
import gr.aueb.sweng22.team05.domain.AgreementType;
import gr.aueb.sweng22.team05.domain.emplType;
import gr.aueb.sweng22.team05.domain.typeOfAgr;

public class AddAgreementTypePresenter {
    private AddAgreementTypeView view;
    private AgreementTypeDAO agreementTypes;
    private AgreementType agrType;

    /**
     * Initialises Presenter
     *
     * @param view           and instance of view
     * @param agreementTypes an instance of agreement type
     */
    public AddAgreementTypePresenter(AddAgreementTypeView view, AgreementTypeDAO agreementTypes) {
        this.view = view;
        this.agreementTypes = agreementTypes;
    }

    /**
     * Saves the new AgreementType with the user's input
     * and checks that name, salary, rest days and sick days are valid
     */
    public void onSaveAgreementType() {
        String
                agrName = view.getName(),
                sal = view.getSalary(),
                restD = view.getRestDays(),
                sickD = view.getSickDays();
        Boolean
                fullT = view.getFullTime(),
                //partT = view.getPartTime(),
                paidByS = view.getPaidBySalary();
        //paidByW = view.getPaidByWage();

        if (agrName.length() <= 0) {
            view.showErrorMessage("Error!", "Name must be at least one character.");
        } else if (sal.split("\\.")[1].length() > 2) {
            view.showErrorMessage("Error!", "Τhe salary must be at most two decimal places.");
        } else {
            typeOfAgr tOA = typeOfAgr.PARTTIME;
            if (fullT) {
                tOA = typeOfAgr.FULLTIME;
            }

            emplType eT = emplType.PAIDBYWAGE;
            if (paidByS) {
                eT = emplType.PAIDBYSALARY;
            }

            agrType = new AgreementType(agreementTypes.nextId(), agrName, Float.parseFloat(sal), tOA, eT, Integer.parseInt(restD), Integer.parseInt(sickD));
            agreementTypes.save(agrType);
            view.setAgreementTypeResult(String.valueOf(agrType.getId()));
            view.successfullyFinishActivity("Agreement Type " + agrType.getId() + " successfully created!");
        }
    }
}
