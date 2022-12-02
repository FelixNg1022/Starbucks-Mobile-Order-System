# Starbucks Order System Project

## What is it:

In this project, I will create an application similar
to a food tracker, but is catered towards keeping track
of Starbucks orders. This app will contain several lists
(*subject to change*):
- an *Ordered* list
- a *Making* list
- and a *Made* list

As an ex-Starbucks barista, I'll implement varieties
of modifications to an Espresso drink to mimic the 
ordering process. Users will be able to add and remove
drinks from the lists, and modify the drinks in the lists.

When a drink is ordered, the drink will be automatically
assigned the status of "Ordered". In the real-life Starbucks
system, the status of the drink will be changed given 2 
conditions. When the sticker of the drink gets pulled 
from the sticker machine, the status will be changed to 
"Making". When the drink is being made, the barista has to
manually update the status of the drink to "Made".

However, since this application does not have any input
sources to help determine the changes of the drinks' status,
the user has to manually update the status of the drink
for the drink to be organized into the corresponding 
drink list.

This project is of interest to me because I'm an ex-Starbucks
barista, and want to design a tracking app that can mimic the
till system I'm really familiar with.

#### (Please not: the users of this system are employees of Starbucks. In other words, the users are not customers.)

## User Stories:

- As a user, I want to be able to let customers order a drink and add the drink to the drink list.
- As a user, I want to be able to let customers cancel their drink orders given that the drink is not currently being made or made.
- As a user, I want to be able to view all the drinks in a list at once.
- As a user, I want to be able to check the number of drinks in each list.
- As a user, I want to be able to store the drink lists in a file for future use.
- As a user, I want to be able to load a stored file to the system to resume previous work, in cases of emergencies, such as power outage.