package afkt_replace.module.template

import afkt_replace.core.lib.base.app.BaseViewModel

class TemplateViewModel(
    private val repository: TemplateRepository = TemplateRepository()
) : BaseViewModel()