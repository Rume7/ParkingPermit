# Parking Permit

A local town is looking to task your group with creating an application that will oversee
provisioning parking permits. The tools and infrastructure are in place, the front-end will be
created by another team. Your team needs to provide a solution for the Java-based middle
tier.

Initially, the town will issue parking permits for three types of vehicles :
    1.) Private use cars – these will be charged £20 per month per registered owner
    2.) Building site vehicles – i.e. lorries and trucks – these are charged £30 per month and
        £5 for every 20kg above 150kg of their capacity
    3.) Motorbikes – the charge is £7 per month for a scooter ( up to 850 cc) and £10 per
month for larger motorbikes

Every vehicle has a unique number plate and at least one registered owner. Only a registered
owner can request a parking permission in the town – if the requester in not the registered
owner the permission should not be issued. If the request was successful, the permit number
will be issued.

The town council would like to keep track of the number of different vehicles with parking
permit so that they could track the traffic.
Develop the above domain model, think carefully what data structures would be suitable to
capture the nuances of the requirements.
