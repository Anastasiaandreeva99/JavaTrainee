package com.nevexis.consumerSupplier;

@FunctionalInterface
public interface SupplierSi<T> {
  T get();
}