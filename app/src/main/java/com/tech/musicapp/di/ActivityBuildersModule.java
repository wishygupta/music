package com.tech.musicapp.di;



import com.tech.musicapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {MainFragmentsBuildersModule.class})
    abstract MainActivity contributeMainActivity();


}
