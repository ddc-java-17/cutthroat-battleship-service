package edu.cnm.deepdive.jata.model.dao;

public interface ShipValid {

  /**
   * returns x and y for ships to determine validity of ship construction
   * going away in next milestone
   * @return
   */
  int[] getX();

  int[] getY();
}
