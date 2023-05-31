// Code Origin: CSCI 1933 Lecture Examples
// permission to use this code was given in the Project 4 Description

// Queue Interface

public interface Q {

// Interface for a Queue

    void add(Object o);

    /* adds an object o to a queue placing it in the order of arrival
       relative to other items added to the queue--first in, first out
       (FIFO) */

    Object remove();

    /* removes and returns the object placed in a queue prior
       to any other items presently in the queue */

    int length();

    /* returns the integer quantity of items currently present in the
       queue */

}  // Q Interface
