package com.nevexis.classes;

public interface InterceptorInterface {
  public void addPurchase(int customer,int amount,int points);
  public InterceptorInterface getNext();
  public void setNext(InterceptorInterface next);
  
}
