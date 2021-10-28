package com.nevexis.consumerSupplier;

@FunctionalInterface
public interface ConsumerSi<T> {
  void accept(T t);
}