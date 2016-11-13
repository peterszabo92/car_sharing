package hu.szakdolgozat.carsharing.component;

import javax.inject.Singleton;

import dagger.Component;
import hu.szakdolgozat.carsharing.login.LoginPresenter;
import hu.szakdolgozat.carsharing.module.ControllerModule;

@Singleton
@Component(modules = ControllerModule.class)
public interface ControllerComponent {

    void inject(LoginPresenter presenter);

}
