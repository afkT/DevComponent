package afkt_replace.module.person

import afkt_replace.core.base.app.BaseViewModel

class PersonViewModel(
    private val repository: PersonRepository = PersonRepository()
) : afkt_replace.core.base.app.BaseViewModel()