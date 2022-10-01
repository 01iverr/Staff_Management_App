# ΠΧ2.  Υπολογισμός μισθοδοσίας

**Πρωτεύων Actor**: Τμήμα Προσωπικού 

**Ενδιαφερόμενοι**<br/>
*Τμήμα Προσωπικού*: θέλει να υπολογίζεται ορθά η μισθοδοσία των εργαζομένων.
*Εργαζόμενοι*: θέλουν οι αποδοχές τους να αναλογούν με τις ώρες που εργάζονται για να μην θέτεται σε κίνδυνο η εμπιστοσύνη τους απέναντι στην εταιρεία.

**Προϋποθέσεις**: <br/>
Ο εργαζόμενος του τμήματος να έχει ταυτοποιηθεί από το Σύστημα .
Υπάρχει σύνδεση του συστήματος με την βάση δεδομένων που περιέχει τις ώρες προέλευσης/αποχώρησης και της βάσης δεδομένων που αποθηκεύονται οι άδειες που έχει λάβει ο εκάστοτε εργαζόμενος.<br/>

**Βασική Ροή**<br/>
1)	O υπάλληλος του τμήματος Προσωπικού επιλέγει ότι θέλει να υπολογιστεί η μισθοδοσία όλων των εργαζομένων.
2)	Το σύστημα ελέγχει για τον εργαζόμενο τον τύπο σύμβασης που έχει καταχωρηθεί, τις ώρες προέλευσης και αποχώρησης και το ύψος του μισθού.
3)	Το σύστημα υπολογίζει τις αποδοχές που του εργαζομένου με τον εξής τύπο: 
(ώρες που έχει δουλέψει * ωρομίσθιο) + (υπερωρίες * ωρομίσθιο * 1.25) + (άδειες ανάπαυσης * ώρες σύμβασης(4 ή 8) * ωρομισθιο) + (άδειες αναρωτικής * ώρες σύμβασης(4 ή 8) * ωρομίσθιο/2). 
4)	To σύστημα καταχωρεί σε βάση ότι στον συγκεκριμένο εργαζόμενο αντιστοιχεί η εν λόγω μισθοδοσία για τον συγκεκριμένο μήνα.
5)	Το σύστημα επαναλαμβάνει τα βήματα 2-4 έως ότου ολοκληρωθεί η διαδικασία για όλους τους εργαζομένους.

**Εναλλακτικές Ροές**

*Α) Σε οποιοδήποτε σημείο το λογισμικό καταρρέει*
1)	Το σύστημα κάνει επανεκκίνηση
2)	Το σύστημα ταυτοποιεί τον υπάλληλο
3)	Ο υπάλληλος εκκινεί την περίπτωση χρήσης από την αρχή 

*Β) Ο εργαζόμενος είναι υπάλληλος μισθωτός και έχει προσληφθεί πριν από αυτό το μήνα.*

    3) α. Ο μισθός ισούται με τον μισθό της σύμβασης - τον μισθό της σύμβασης/25/2 * άδειες αναρωτικής.


*Γ) Ο εργαζόμενος είναι  υπάλληλος ωρωμίσθιος και έχει προσληφθεί αυτό το μήνα.*
    
    3) β. Ο μισθός ισούται με τον μισθό της σύμβασης διαιρούμενο δια 25 πολλαπλασιαζόμενο επί της μέρες που δούλεψε ή πήρε άδεια ανάπαυσης συν  μισθό της σύμβασης διαιρούμενο δια 25 και έπειτα δια 2 επί της ημέρες που έλαβε αναρρωτική εκείνο το μήνα.

*Δ) Ο εργαζόμενος είναι υπεύθυνος ωρωμίσθιος και έχει προσληφθεί αυτό το μήνα.*

    3) β. Ο μισθός ισούται με τον μισθό της σύμβασης διαιρούμενο δια 25 πολλαπλασιαζόμενο επί της μέρες που δούλεψε

*Ε) Ο εργαζόμενος είναι υπεύθυνος μισθωτός και έχει προσληφθεί πριν από αυτό το μήνα.*

    3) α. Ο μισθός ισούται με τον μισθό της σύμβασης - τον μισθό της σύμβασης/25/2 * άδειες αναρωτικής.




<br/>
<br/>

**Διάγραμμα ακολουθίας**
---
<br/>
<br/>

![sequence calculate payments ](/docs/markdown/uml/requirements/sequence-calc-pay.png)
![sequence Manager calPay ](/docs/markdown/uml/requirements/sequence-Manager-calcPay.png)
![sequence Employee calPay ](/docs/markdown/uml/requirements/sequence-calcPay.png)
![sequence ref1 ](/docs/markdown/uml/requirements/sequence-findThePreviousMonth-thereIsPreviousAgreement-thisAgreementStartedBeforethisMonth.png)
![sequence ref2 ](/docs/markdown/uml/requirements/sequence-paymentForPaidByHour-PaymentwithextraMinsForADay-paymentByMin.png)
![sequence ref3 ](/docs/markdown/uml/requirements/sequence-daysOfWorkThisMonth-minsOfWorkingtoday-paidByHour-paymentByMinMonth.png)
![sequence ref4 ](/docs/markdown/uml/requirements/sequence-paymentForPaidByHour-PaymentwithextraMinsForADay-paymentByMin.png)
![sequence ref5 ](/docs/markdown/uml/requirements/sequence-paymentForSickANDRestDays.png)
![sequence ref6 ](/docs/markdown/uml/requirements/sequence-paymentForSickDays.png)
![sequence ref7 ](/docs/markdown/uml/requirements/sequence-paymentRSDAYS-daysOfLeave.png)
![sequence ref8 ](/docs/markdown/uml/requirements/sequence-totalRestDays.png)


