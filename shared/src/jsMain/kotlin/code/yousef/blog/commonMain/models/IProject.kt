package code.yousef.blog.models

import com.benasher44.uuid.Uuid

interface IProject {
    var id: Uuid
    var projectName: String
    var projectNameArabic: String
    var projectSlug: String?
    var projectHeader: String
    var projectDescription: String
    var projectDescriptionArabic: String
    var projectSummary: String
    var projectSummaryArabic: String
    var projectScreenShots: List<String>
    var github: String?
    var website: String?
    var playStore: String?
    var appStore: String?
    var twitter: String?
    var instagram: String?
}