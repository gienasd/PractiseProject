import java.io.Serializable;

public class Person<T, U> implements Serializable{

  private T t;
  private U u;

    public Person() {
    }

    public Person(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getT() { return t; }
    public void setT(T t) { this.t = t; }

    public U getU() { return u; }
    public void setU(U u) { this.u = u; }

  @Override
  public String toString() {
    return getT()+ ", ID: " + getU();
  }
}

