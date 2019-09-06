package energy.adesso.adessoandroidapp.logic.model;

public class Either<A, B> {
  private A a;
  private B b;

  public Either(A a, B b) {
    this.a = a;
    this.b = b;
  }

  public A getA() {
    return a;
  }

  public B getB() {
    return b;
  }

  public int getIndex() {
    if (a != null && b != null) throw new IllegalStateException();
    return a == null ? 0 : 1;
  }

}
