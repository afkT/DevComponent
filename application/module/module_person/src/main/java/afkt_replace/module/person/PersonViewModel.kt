package afkt_replace.module.person

import afkt_replace.core.lib.base.app.BaseViewModel

class PersonViewModel(
    private val repository: PersonRepository = PersonRepository()
) : BaseViewModel()