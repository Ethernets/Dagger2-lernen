package com.example.daggerlernen.common.dependencyinjection.activity

import com.example.daggerlernen.common.dependencyinjection.app.AppComponent
import com.example.daggerlernen.common.dependencyinjection.presentation.PresentationComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
   fun newPresentationComponent(): PresentationComponent
}