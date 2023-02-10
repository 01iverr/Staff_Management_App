# Staff Management app

## Description

This is a group project, created as part of the course Software Engineering at AUEB (that is the reason a big part of the project is written in greek).

The diagrams were made using the VS Code extension [UMLet](https://marketplace.visualstudio.com/items?itemName=TheUMLetTeam.umlet).

The app was created in 
**Java**, using [Android Studio](https://developer.android.com/studio), and includes **JUnit tests**.
## Project analysis

The end goal was to create an android app that can be used to manage the staff of a company. Each worker can log in with their credentials, and managers specifically can manage the accounts of each employee in their department. That includes creating/editing/deleting their accounts from the system, calculate payments, and manage leave requests placed by the employees.

The project was done in parts (originally uploaded on a private gitlab repository created for us for the purpose of this project).

Each part was meant to emulate a different part of the creation of such a project, as this would have been potentially created in a real setting. This includes the design process, with all the needed diagrams and descriptions, the code, the application itself, and all the needed tests with the best possible coverage.

## Project parts

* R1

    [Περιγραφή Απαιτήσεων](/docs/markdown/R1-software-requirements.md)

* R2

    [Περιπτώσεις Χρήσης και συμπληρωματικές προδιαγραφές για τις μη Λειτουργικές Απαιτήσεις](/docs/markdown/R2.md)

    Domain model:
    ![domain model](/docs/markdown/uml/requirements/domain-model.png "Domain model")

* R3

    Class Diagram:
![class diagram](/docs/markdown/uml/requirements/class-diagram.png "Class diagram")

* R4
  
    Class Diagram for DAO:
![class diagram for DAO](/docs/markdown/uml/requirements/class-diagram-dao-structure.png "Class diagram for DAO")
  
    Class Diagram for AddAgreement:
![class diagram for AddAgreement](/docs/markdown/uml/requirements/class-diagram-addAgreement.png "Class diagram for AddAgreement")

    Sequence for AddAgreement:
![sequence diagram for AddAgreement](/docs/markdown/uml/requirements/sequence-addAgreement.png "Sequence diagram for AddAgreement")


    Class Diagram for AddAgreementType:
![class diagram for AddAgreementType](/docs/markdown/uml/requirements/class-diagram-addAgreementType.png "Class diagram for AddAgreementType")

    Sequence for AddAgreementType:
![sequence diagram for AddAgreementType](/docs/markdown/uml/requirements/sequence-addAgreementType.png "Sequence diagram for AddAgreementType")


    Class Diagram for AddWorker:
![class diagram for AddWorker](/docs/markdown/uml/requirements/class-diagram-addWorker.png "Class diagram for AddWorker")

    Sequence for AddWorker:
![sequence diagram for AddWorker](/docs/markdown/uml/requirements/sequence-AddWorker.png "Sequence diagram for AddWorker")


    Class Diagram for EditWorker:
![class diagram for EditWorker](/docs/markdown/uml/requirements/class-diagram-EditWorker.png "Class diagram for EditWorker")

    Sequence for EditWorker:
![sequence diagram for EditWorker](/docs/markdown/uml/requirements/sequence-EditWorker.png "Sequence diagram for EditWorker")


    Class Diagram for HandleLeaveRequest:
![class diagram for HandleLeaveRequest](/docs/markdown/uml/requirements/class-diagram-HandleLeaveRequest.png "Class diagram for HandleLeaveRequest")

    Sequence for HandleLeaveRequest:
![sequence diagram for HandleLeaveRequest](docs/markdown/uml/requirements/sequence-HandleLeaveRequest.png "Sequence diagram for HandleLeaveRequest")


    Class Diagram for HomePage:
![class diagram for HomePage](/docs/markdown/uml/requirements/class-diagram-HomePage.png "Class diagram for HomePage")

    Sequence for HomePage:
![sequence diagram for HomePage](docs/markdown/uml/requirements/sequence-HomePage.png "Sequence diagram for HomePage")


    Class Diagram for hrPage:
![class diagram for hrPage](/docs/markdown/uml/requirements/class-diagram-hrPage.png "Class diagram for hrPage")

    Sequence for hrPage:
![sequence diagram for hrPage](docs/markdown/uml/requirements/sequence-HrPage.png "Sequence diagram for hrPage")


    Class Diagram for LeaveRequest:
![class diagram for LeaveRequest](/docs/markdown/uml/requirements/class-diagram-LeaveRequest.png "Class diagram for LeaveRequest")

    Sequence for LeaveRequest:
![sequence diagram for LeaveRequest](docs/markdown/uml/requirements/sequence-LeaveRequest.png "Sequence diagram for LeaveRequest")


    Class Diagram for SearchWorkerByAfm:
![class diagram for SearchWorkerByAfm](/docs/markdown/uml/requirements/class-diagram-search_worker_by_afm.png "Class diagram for SearchWorkerByAfm")

    Sequence for SearchWorkerByAfm:
![sequence diagram for SearchWorkerByAfm](docs/markdown/uml/requirements/sequence-SearchWorkerByAfm.png "Sequence diagram for SearchWorkerByAfm")


    Class Diagram for ShowLeaveRequests:
![class diagram for ShowLeaveRequests](/docs/markdown/uml/requirements/class-diagram-ShowLeaveRequests.png "Class diagram for ShowLeaveRequests")
