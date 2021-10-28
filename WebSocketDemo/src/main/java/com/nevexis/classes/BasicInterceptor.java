package com.nevexis.classes;

public abstract class BasicInterceptor implements InterceptorInterface{
	   protected InterceptorInterface next;

		

		@Override
		public InterceptorInterface getNext() {
			return next;
		}

		@Override
		public void setNext(InterceptorInterface next) {
			this.next=next;
		}
}
