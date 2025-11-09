package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before public void before(){ /* init if needed */ }
    @After  public void after(){  /* cleanup if needed */ }
}
